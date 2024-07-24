package com.craftinginterpreters.lox;

import java.util.List;

class LoxFunction implements LoxCallable {
    private final Stmt.Function declaration;
    public LoxFunction(Stmt.Function declaration){
        this.declaration = declaration;
    }

    @Override
    public int arity() {
        return declaration.params.size();
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> argumets) {
        Environment environment = new Environment(interpreter.globals);
        declaration.params.forEach(param -> environment.define(param.lexeme, param));
        interpreter.executeBlock(declaration.body, environment);
        return null;
    }
    
    @Override
    public String toString(){
        return "<fn " + declaration.name.lexeme + ">";
    }
}
