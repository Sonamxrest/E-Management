pipeline {
	agent any
	
	stages {
		stage('Build'){
			steps {
				sh "mvn clean install"
			}
		}
		stage('Image') {
			steps {
				sh "docker build -t emanagement ."
				sh "docker ps"
			}
		}
		stage('Test'){
			steps{
				sh "mvn test"
			}
		}
		stage('Deploy') {
			steps {
			    echo "Run Passed"
			}
		}
	}
}
