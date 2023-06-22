def call(String repoUrl) {
       pipeline {
        agent any

   
        stages { 
            stage('checkout') {
                environment {
                        Branch_Name='main'
                }
                steps{  
                    git branch:'main', url: "${repoUrl}"
            }
            stage('code test') {
                steps {
                    sh ''' 
                        /opt/homebrew/bin/mvn -version
                        /opt/homebrew/bin/mvn test
                    '''
    
                }
            }
            stage('CodeQualityAnalysis') {
                steps {
            sh '''
            /opt/homebrew/bin/mvn clean verify sonar:sonar -Dsonar.projectKey=java-base-project -Dsonar.projectName='java-base-project' -Dsonar.token='sqp_6fcb7b67b06313ad7315c3bc4c11bcf39f3eb64e'
            '''
    
                }
            }
            stage ('CodeBuild') {
                steps {
                    sh """
                        echo "getting Project Programing Lang:-" $Programming_project
                        /opt/homebrew/bin/mvn clean install
                    """              
                    
                }
            }
            stage ('SnapShotBuild') {
                steps {
                    sh '/opt/homebrew/bin/mvn clean deploy -Dmaven.test.skip=true -s settings.xml'
                }
            }
            stage ('ReleaseArtifact') {
                steps{
                    sh ''' 
                       echo "release artifact)
                       # /opt/homebrew/bin/mvn -U -e  release:clean release:prepare -Dmaven.test.skip=true -s settings.xml
                       # /opt/homebrew/bin/mvn  -U -e  release:perform -Dmaven.test.skip=true -s settings.xml
                    '''

                }
            }
        }
        
    }
    
       }
}
