# R++ Compiler

R++ is a custom programming language with a simple syntax designed for learning and experimenting with compiler design.

---

## Requirements

- **Java Development Kit (JDK)** installed on your system (version 17 or above recommended).
- **Command line / terminal** to run programs.

---

# Running R++ Programs

The repository already contains `rpp.jar` in the root.  
To run an `.rpp` file, use:

```bash
java -jar rpp.jar <your_file>.rpp
```

# R++ Syntax

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

let i = 0;                        // redefine i after for loop, won't give error because of scope of variable..
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
