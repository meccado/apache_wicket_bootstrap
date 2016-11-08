#!groovy
node ('master') {

   step([$class: 'WsCleanup'])
   // Mark the code checkout 'stage'....
   stage 'Checkout'

   // Checkout code from repository
   checkout scm

   def v = version()
   if(v){
       echo "Building version ${v}"
   }

   // Mark the code build 'stage'....
   stage 'Build'
   // Run the maven build
   withEnv(["PATH+MAVEN=${tool 'M3'}/bin"]) {
        if(isUnix()){
            sh 'mvn -B  -Dmaven.test.failure.ignore clean install'
        }else{
            bat 'mvn -B  -Dmaven.test.failure.ignore clean install'
        }   
   }
   step([$class: 'ArtifactArchiver', artifacts: '**/target/*.war', fingerprint: true])

stage name: 'TEST', concurrency: 1
		// Integration Test Module 1
		parallel    'INTEGRATION': {
		                node{
	                        unstash 'source'
	                        withEnv(["PATH+MAVEN=${tool 'M3'}/bin"]) {
	                            dir('parent'){
    	                            if(isUnix()){
    	                                sh "mvn clean verify"
    	                            }else{
    	                               bat "mvn clean verify" 
    	                            }
	                            }
                            }
		                }
		            },
                    'QUALITY':  {
                        node{
	                        unstash 'source'
	                        withEnv(["PATH+MAVEN=${tool 'M3'}/bin"]) {
	                            dir('parent'){
    	                            if(isUnix()){
    	                                //sh "mvn sonar:sonar"
    	                            }else{
    	                                //bat "mvn sonar:sonar"
    	                            }
	                            }
                            }
		                }
                    },
                    failFast: true
		junit '**/target/surefire-reports/TEST-*.xml'
    stage('SonarQube analysis') {
        // requires SonarQube Scanner 2.8+
        def scannerHome = tool 'SonarQube Scanner 2.8';
        withSonarQubeEnv('SonarQube 6.1') {
            dir('parent') {
                if(isUnix()){
                    sh "${scannerHome}/bin/sonar-scanner -X"
                }else{
                    bat "${scannerHome}/bin/sonar-scanner -X"  
                }
            }
        }
    }
}

def version(){
    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
    matcher ? matcher[0][1] : null
}

def mvn(args) {
    sh "${tool 'M3'}/bin/mvn ${args}"
 }
