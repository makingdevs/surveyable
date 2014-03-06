package com.makingdevs

class QuestionService {

  def crearPreguntaConCalificacion(def params){
    def pregunta = new Question(description:params.description,
                                questionType:params.questionType,
                                satisfaccion:params.from.toInteger()..params.to.toInteger()).save(flush:true)
    pregunta
  }

  def crearPreguntaGenerica(def params){
    def pregunta = new Question(params).save(flush:true)
    pregunta
  }

}