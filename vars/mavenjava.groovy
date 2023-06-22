def call(String repoUrl) {
    pipeline {
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
