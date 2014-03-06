package com.makingdevs

import com.makingdevs.*

class QuestionController {

  def questionService

  def index() { }

  def save(){
    def pregunta

    if(params.from&&params.to) pregunta = questionService.crearPreguntaConCalificacion(params)
    else pregunta = questionService.crearPreguntaGenerica(params)

    if(pregunta.id>0)
      redirect action:"show", id:pregunta.id
    else
      render view:"index"
  }

  def show(){
    def question = Question.get(params.id)
    [question:question]
  }

  def list(){
    def listQuestion = Question.list()
    [listQuestion:listQuestion]
  }

  def detail(){
    def question = Question.get(params.id)
    [question:question]
  }

}