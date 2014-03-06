package com.makingdevs

class SurveyPerInstanceService {

  def instanceSurvey(surveyId){
    def survey = Survey.get(surveyId)
    def surveyPerInstance = new SurveyPerInstance(survey:survey)
    survey.questions.each{question->
      surveyPerInstance.addToAnswerPerInstances(new AnswerPerInstance(question:question))
    }
    surveyPerInstance.save(flush:true)
    surveyPerInstance
  }

  def addAnswer(idPregunta,respuesta,satisfaccion,observaciones,surveyId){
    def question = Question.get(idPregunta)
    def surveyPerInstance = SurveyPerInstance.get(surveyId)
    surveyPerInstance.answerPerInstances.each{answerPerInstance->
      if(answerPerInstance.question.id==question.id){
        switch(question.questionType) {
          case QuestionType.CON_CALIFICACION:
            def answer=Answer.get(respuesta)
            answerPerInstance.addToAnswerPerUsers(new AnswerPerUser(answer:answer)).save()
            answerPerInstance.addToRangeAnswerPerUsers(new RangeAnswerPerUser(userAnswer:satisfaccion)).save()
            answerPerInstance.addToOpenAnswerPerUsers(new OpenAnswerPerUser(userAnswer:observaciones)).save()
          break
          case QuestionType.SIN_CALIFICACION:
            def answer=Answer.get(respuesta)
            answerPerInstance.addToAnswerPerUsers(new AnswerPerUser(answer:answer)).save()
            answerPerInstance.addToOpenAnswerPerUsers(new OpenAnswerPerUser(userAnswer:observaciones)).save()
          break
          case QuestionType.ABIERTA:
            answerPerInstance.addToOpenAnswerPerUsers(new OpenAnswerPerUser(userAnswer:respuesta)).save()
          break
        }
      }
    }
    surveyPerInstance.save()
  }

}