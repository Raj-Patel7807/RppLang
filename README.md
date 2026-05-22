# R++ Compiler 🚀

R++ is a custom interpreted programming language built in Java for learning and experimenting with compiler design concepts.

The project implements the complete compilation pipeline including:

- Lexical Analysis
- Parsing
- AST Generation
- Runtime Execution
- Scoped Environment Handling
- Loop Control Flow (`break` / `continue`)
- Error Handling System

R++ is designed with a simple and beginner-friendly syntax while internally following real compiler architecture patterns.

---

## ✨ Features

- Custom language syntax
- Variable declarations and assignments
- Arithmetic expressions
- String handling
- Conditional statements
- Loops (`for`, `while`, `do-while`)
- `break` and `continue`
- Scoped variable environments
- AST-based execution
- Custom lexer, parser, and runtime
- Runtime / parser / lexer error handling
- Unit testing with JUnit
- CLI based execution

---

# 🏗️ Compiler Architecture

```text
Source Code (.rpp)
        ↓
      Lexer
        ↓
      Tokens
        ↓
      Parser
        ↓
 Abstract Syntax Tree (AST)
        ↓
     Runtime
        ↓
      Output
```

---

# 🚀 Running R++ Programs

## ⚙️ Requirements

- Java JDK 17 or above
- Maven
- Terminal / Command Prompt

---

## Running Programs
The repository already contains `rpp.jar` in the root directory.

Run any `.rpp` file using:

```bash
java -jar rpp.jar <your_file>.rpp
```

Example:

```bash
java -jar rpp.jar hello.rpp
```

---

# 🧠 R++ Syntax

```bash
let x = 10;
let y = 100;
let z = x + y;                  // you can do any arithmetic operations;
let temp;
let name = "Tony Stark";
let float = 7.8;
let a = 10;
a = a + 10;
a = x + y + a * 10;

print("Hello, World!");
print(x + y);                   // any arithmetic ops;
print(z);
print("Value of z = " + z);
print("Hello" + " World..");
print(name);

// This is a Single Line comment
/*
This is a Multi Line Comment
This is a Multi Line Comment
*/

let marks = 99;
if(marks >= 91) {
  print("Grade A");
} else if(marks >= 81) {
  print("Grade B");
} else if(marks >= 33) {
  print("Pass");
} else {
  print("Fail");
}

for(let i=0; i<5; i=i+1) {
  if(i == 3) {
    break;                            // can also use "continue;"
  }
  print("i = " + i);
}

let i = 0;       // redefine i after for loop, will not give error because of scope of variable..
while(i < 5) {
  if(i == 3) { break; }
  print("i = " + i);
  i = i + 1;
}

let j = 0;
do {
  print("j = " + j);
} while(j < 0);
```

---

# 🔍 Supported Language Features

| Category | Features |
|---|---|
| Variables | Declaration, Assignment, Reassignment |
| Data Types | Numbers, Strings, Booleans |
| Operators | Arithmetic, Comparison |
| Control Flow | `if`, `else if`, `else` |
| Loops | `for`, `while`, `do-while` |
| Loop Control | `break`, `continue` |
| Output | `print()` |
| Comments | Single-line and Multi-line |
| Scope Handling | Block Scoped Variables |
| Architecture | Lexer → Parser → AST → Runtime |
| Error Handling | Lexer, Parser, Runtime Errors |

---

# 🛠️ Tech Stack

- Java 17+
- Maven

---

# 📌 Future Improvements

- Functions
- Arrays
- Objects / Classes
- File handling
- User input support
- Better type system
- Standard library

---

Made by Raj Patel.
