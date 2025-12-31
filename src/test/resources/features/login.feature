# language: pt

Funcionalidade: Login Consultor

  @CT001
  Cenario: Login valido

    Dado  que o usuario esteja no gtt dev
    Quando  preenche email e senha
    Então deve ser apresentada a mensagem de boas vindas

  @CT002
  Cenario: Login inválido
    Dado  que o usuario esteja no gtt dev
    Quando  preenche email e senha invalido
    Então deve ser apresentada uma mensagem informando usuário e senha invalido



