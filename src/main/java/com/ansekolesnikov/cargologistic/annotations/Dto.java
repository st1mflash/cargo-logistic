package com.ansekolesnikov.cargologistic.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

//todo для классов сущностей не делают такие интерфейсы (только если считать это маркером, но у тебя в названии классов уже указано Dto)
@Target(ElementType.TYPE)
public @interface Dto {

}
