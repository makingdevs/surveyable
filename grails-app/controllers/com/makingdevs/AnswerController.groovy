package com.makingdevs

class AnswerController {

    def create() { 
      def question = Question.get(params.id)
    [question:question]
    }

    def agregar(){
      println params
      def question = Question.get(params.question)
      def answer = new Answer(description:params.description, answerType:params.answerType)
      question.addToAnswers(answer)
      question.save()
      redirect(controller: "question", action: "show", id:question.id)

    }

}
