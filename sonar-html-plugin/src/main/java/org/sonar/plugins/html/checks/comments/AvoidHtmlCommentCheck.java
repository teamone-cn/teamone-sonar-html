/*
 * SonarHTML :: SonarQube Plugin
 * Copyright (c) 2010-2019 SonarSource SA and Matthijs Galesloot
 * sonarqube@googlegroups.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sonar.plugins.html.checks.comments;

import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;
import org.sonar.plugins.html.checks.AbstractPageCheck;
import org.sonar.plugins.html.node.CommentNode;
import org.sonar.plugins.html.node.Node;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

@Rule(key = "AvoidHtmlCommentCheck")
public class AvoidHtmlCommentCheck extends AbstractPageCheck {

    private boolean isServerSidePage;

    @RuleProperty(
            key = "regex",
            description = "注释内容必须匹配上 \"regex\"")
    public String regex = "";

    @Override
    public void comment(CommentNode node) {
        String comment = node.getCode();
        System.out.println(comment);
        Pattern p = Pattern.compile(".*<!--[^[\\u4e00-\\u9fa5]]+-->$");

        String s1 = "<div class=\"clear\"></div></div><!-- wpbody-content -->";
        String s2 = "<div class=\"clear\"></div></div><!-- 过去啊围观 -->";
        System.out.println(!p.matcher(s1).matches());
        System.out.println(!p.matcher(s2).matches());

        if (!p.matcher(comment).matches()) {
            createViolation(node.getStartLinePosition(), "不应出现不能匹配上正则表达式的注释内容");
        }
    }

    @Override
    public void startDocument(List<Node> nodes) {
        isServerSidePage = false;
        Iterator<Node> iterator = nodes.iterator();
        while (!isServerSidePage && iterator.hasNext()) {
            String code = iterator.next().getCode();
            if (code.startsWith("<?php") || code.startsWith("<%")) {
                isServerSidePage = true;
            }
        }
    }

}
