Java EE - Full Stack for Business Applications
==============================================

by [Matthias Reining](https://twitter.com/MatthiasReining) at FHWS (Hochschule für angewandte Wissenschaften Würzburg-Schweinfurt)

FWMP: 12 SWS


The Course
----------


**Learning Objective:**

> Den Teilnehmern der Vorlesung werden die Grundlagen der Java Enterprise Edition vermittelt. Die Studierenden lernen den Aufbau und die Funktionsweise eines Application Servers kennen. Der Schwerpunkt der Veranstaltung liegt primär bei den APIs der Java EE Spezifikation mit denen sich Unternehmensanwendungen erstellen lassen. Hierbei erlernen die Studierenden wie man Front-Ends, Web Services, Business Logic und Datenpersistenz auf Basis der Java EE entwickelt. Im Rahmen einer Beispielanwendung werden die Konzepte gängiger Software-Patterns vermittelt und aufgezeigt welche Lösungen die Java EE hierfür anbietet. Weiterhin erlangen die Teilnehmer einen Einblick in gängige Build-Techniken bei Java EE Softwareprojekten

**Slides**

Memorize my slides makes absolutely no sense! Understanding the code - that's it!

In any case, you'll find the slides [here](http://tinyurl.com/fhws-javaee-2015).

Furthermore, the slides will be also present at Slideshare (I'll also publish the link, after I will have uploaded them ;-)

**Source Code**

The source code is managed at GitHub ([https://github.com/mr678/fhws-2015](https://github.com/mr678/fhws-2015)).

The source code demonstrate the possibilities of Java EE discussed during the course. The project contains not only nice and smart code based on the latest version of Java EE. Some parts of the code are only for a better understanding of why the latest version of Java EE is clean, thin and smart!

**Infrastrucutre**

The example application is published into the cloud. As cloud [Amazon EC2](https://aws.amazon.com/de/ec2/) is used managed by [bitnami](https://bitnami.com).

(Jenkins)[https://jenkins-ci.org/] is used for build and deployments. Unfortunately, the image doesn't provide a preinstalled Jenkins server. Therefore, Jenkins is installed manually at the server. For installation the following guide is used: [https://wiki.jenkins-ci.org/display/JENKINS/Installing+Jenkins+on+Ubuntu](https://wiki.jenkins-ci.org/display/JENKINS/Installing+Jenkins+on+Ubuntu).

	wget -q -O - https://jenkins-ci.org/debian/jenkins-ci.org.key | sudo apt-key add -
	sudo sh -c 'echo deb http://pkg.jenkins-ci.org/debian binary/ > /etc/apt/sources.list.d/jenkins.list'
	sudo apt-get update
	sudo apt-get install jenkins

The port is also changed to 8081. If you want direct access, without Apache HTTP server, you have to open also the port in the firewall (it's not recommend, but pragmatic in our case...). Thanks to Bitnami this can be done easily via the Bitnami Console.