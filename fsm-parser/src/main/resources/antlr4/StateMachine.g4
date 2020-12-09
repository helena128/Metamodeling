grammar StateMachine;

fsm: 'StateMachine' IDENTIFIER '{' declareBlock executeBlock '}';
declareBlock: states finalState table;
states: 'states:' '(' stateList ')';
finalState: 'finalStates:' '(' stateList ')';
stateList: STATE (SEP STATE)*;
table: 'table:' '(' transitionList ')';
transition: '<' STATE '->' STATE '(' ALPHA ')' '>';
transitionList: transition (SEP transition)* ;
executeBlock: 'exec' IDENTIFIER '{' IDENTIFIER '}';

ALPHA: [a-zA-Z];
STATE: [s][0-9]+;
IDENTIFIER: [a-zA-Z][a-zA-Z0-9]+;
SEP  : ',';
NUM  : [0-9]+;
WS   : [ \t\r\n]+ -> skip;