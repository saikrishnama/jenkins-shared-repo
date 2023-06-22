def call(String repoUrl) {
    pipeline {
        tools {
            maven 'Maven 3.9.2'
        }
        agent any
        stages{
            stage('Checkout'){
                steps {
                    git branch:'main',url: "${repoUrl}"
                }
        }
            stage ('Maven -version')
            {
                steos {
                    sh '''
                            mvn -version
                            java -version 

                    '''
                }
            }
    }
    }
}
