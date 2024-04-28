
# Teamone Sonar PHP
Teamone Sonar HTML 是基于 SonarHTML 修改部分规则和自定义规则，用于对开发工程师书写的 HTML 代码进行检测和bug提示，帮助建立代码规范，养成编码习惯，提升编码质量

# 主要特性
1. 新增了1种规则分别为：对中文注释进行bug提示（支持正则）
2. 修改源代码中存在的其他导致检测不到的bug，完善一些检查机制

# 安装方式
执行如下打包命令
```shell
mvn license:format

mvn clean install -DskipTests
```

将打出来的 jar 包放置在 Sonar 对应的 plugins 的 jar 包文件夹下

# 其他可参考官网readme：

SonarQube HTML Plugin
====================
[![Build Status](https://api.travis-ci.org/SonarSource/sonar-html.svg)](https://travis-ci.org/SonarSource/sonar-html)

Project homepage:
http://redirect.sonarsource.com/plugins/web.html

Issue tracking:
http://jira.sonarsource.com/browse/SONARHTML

### License

Copyright 2010-2018 SonarSource.

Licensed under the [GNU Lesser General Public License, Version 3.0](http://www.gnu.org/licenses/lgpl.txt)
