#!groovy
stage 'Dev'
node ('master') {
    checkout scm
    mvn 'clean package'
}
