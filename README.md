This was a two part assignment, where we designed a mini programming language based off the rules we learned in the class about syntax and grammar, and then using a high-level programming language,
wrote a parser for any given statements


This the mini Programming Language I designed for this project:


assignment = (“asg” variable value);

incrementation = (“++” variable);

conditional = (“>?” variable variable) | (“<?” variable variable) | (“=?” variable variable) | ( “=/?” variable  variable) | (=? (variable variable) ;

conditional = (“if” (conditional) (then) | (else));

for-loop = (“for” “[“ (conditional)  (conditional) “]”) ;

display = (“put” variable | value) ;

variable = letter, {letter};

letter = "a" | "b" | "c" | "d" | "e" | "f" | "g" | "h" | "i" | "j" | "k" | "l" | "m" | "n" | "o" | "p" | "q" | "r" | "s" | "t" | "u" | "v" | "w" | "x" | "y" | "z" ;

value = number;

number = digit, {digit};

digit = “0” | “1” | “2” | “3” | “4” | “5” | “6” | “7” | “8” | “9” ;

Example programs are included in the main.clj file
