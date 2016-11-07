#!groovy
stage 'Dev'
node ('master') {

   // Mark the code checkout 'stage'....
   stage 'Checkout'

   // Checkout code from repository
   checkout scm

   // Get the maven tool.
   // ** NOTE: This 'M3' maven tool must be configured
   // **       in the global configuration.
   def mvnHome = tool 'M3'

   // Mark the code build 'stage'....
   stage 'Build'
   // Run the maven build
   if(isUnix()){
        sh "${mvnHome}/bin/mvn clean install"
   }else{
        bat "${mvnHome}/bin/mvn clean install"
   }
}