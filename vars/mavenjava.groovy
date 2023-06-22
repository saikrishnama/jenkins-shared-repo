def call(String repoUrl) {
    pipeline {
        agent any
        stages{
            stage('Checkout'){
                steps {
                    git branch:'main',url: "${repoUrl}"
                }
        }
    }
    }
}
