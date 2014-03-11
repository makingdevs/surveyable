package com.makingdevs

class SurveyPerInstanceService {

  def instanceSurvey(surveyId){
    def survey = Survey.get(surveyId)
    def surveyPerInstance = new SurveyPerInstance(survey:survey,
                                                  surveyPerInstanceStatus:SurveyPerInstanceStatus.SIN_CONTESTAR)
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

  def saveAnswersInSurvey(def params){

    def idPregunta = []
    def idPreguntas= []
    def respuestas=[]
    def satisfaccion=[]
    def observaciones=[]
    for (int i = 0; i < params.numPreguntas.toLong(); i++) {
      idPregunta << params.getAt("question[${i}]").id
    }
    def preguntas=Question.getAll(idPregunta)
    def surveyPerInstance = SurveyPerInstance.get(params.num)
    for (int i = 0; i < params.numPreguntas.toLong(); i++) {
      switch(preguntas.getAt(i).questionType) {
        case QuestionType.CON_CALIFICACION:
          idPreguntas << params.getAt("question[${i}]").id
          respuestas << params.getAt("question[${i}]").description
          satisfaccion << params.getAt("question[${i}]").rango
          observaciones << params.getAt("question[${i}]").comentario
        break
        case QuestionType.SIN_CALIFICACION:
          idPreguntas << params.getAt("question[${i}]").id
          respuestas << params.getAt("question[${i}]").description
          satisfaccion << null
          observaciones << params.getAt("question[${i}]").comentario
        break
        case QuestionType.ABIERTA:
          idPreguntas << params.getAt("question[${i}]").id
          respuestas << params.getAt("question[${i}]").description
          satisfaccion << null
          observaciones << null
        break
      }
    }
    for (int i = 0; i < params.numPreguntas.toLong(); i++) {
      addAnswer(idPregunta.getAt(i),respuestas.getAt(i),satisfaccion.getAt(i),observaciones.getAt(i),surveyPerInstance.id)
    }
    surveyPerInstance.surveyPerInstanceStatus=SurveyPerInstanceStatus.CONTESTADO
    surveyPerInstance

  }

}