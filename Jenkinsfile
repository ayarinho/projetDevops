node{

  def app
  stage('Clone'){
    checkout Youssef
   }
   
   stage('Build image'){
     
     app=docker.build("ayarinho7/timesheet")
   }
   
   stage("Run image"){
   
    docker.image("ayarinho7/timesheet").withRun('-p 83:83'){c ->
    
      sh 'docker ps'
      sh 'curl localhost'
    }
   }
}