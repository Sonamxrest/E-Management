pipeline {
	agent none
	environment {
		mavenHome = tool 'jenkins-maven'
	}
	tools {
		jdk 'java-17'
	}
  stages {
  	stage('Maven Install') {
   //  	agent {
   //    	docker {
   //      	image 'maven:3.5.0'
   //      }
   //    }
      steps {
      	sh 'mvn clean install'
      }
    }
    stage('Docker Build') {
    	agent any
      steps {
        sh 'docker --version'
      	sh 'docker build -t shanem/spring-petclinic:latest .'
      }
    }
  }
}
// pipeline {
// 	agent { dockerfile true }
// 	environment {
// 		mavenHome = tool 'jenkins-maven'
// 	}
// 	tools {
// 		jdk 'java-17'
// 	}
// 	stages {
// 		stage('Build'){
// 			steps {
// 				sh "mvn install -Dmaven.test.skip=true"
// 			}
// 		}
// 		stage('Image') {
// 			steps {
// 				sh "ls"
// 				sh "docker build -t emanagement ."
// 				sh "docker ps"
// 			}
// 		}
// 		stage('Test'){
// 			steps{
// 				sh "mvn test"
// 			}
// 		}
// 		stage('Deploy') {
// 			steps {
// 			    echo "Run Passed"
// 			}
// 		}
// 	}
// }
