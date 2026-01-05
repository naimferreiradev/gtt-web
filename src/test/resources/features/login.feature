# language: pt

Funcionalidade: Login Consultor

  @CT001
  Cenario: Login valido

    Dado  Que o usuario esteja no gtt dev
    Quando  Preenche email e senha
    Então Deve ser apresentada a mensagem de boas vindas

  @CT002
  Cenario: Login inválido
    Dado  Que o usuario esteja no gtt dev
    Quando  Preenche email e senha invalido
    Então Deve ser apresentada uma mensagem informando usuário e senha invalido



