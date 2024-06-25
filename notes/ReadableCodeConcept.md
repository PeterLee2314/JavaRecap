### Readable Code Concept

#### Inversion
```
// bad :
if (X) {
    if (Y) {
        if(!Z) {
        
        } else {
            throw(Z)
        }
    }else {
        throw(Y)
    }
}else {
throw (X)
}

// good:
if(!X) {
    throw X
}
if(!Y) {
    throw Y
}
if(Z) {
    throw Z
}

```

#### Merged Related If Statement
// if Y,Z is validation of user authenticated and authroized
if (Y || Z) {
    throw (a common message that can represent both Y and Z)
}
#### Extraction
extract it into function
turn the above Merged even inside a method call 
public boolean isValidUser() ...

if(isValidUser()) {
    throw (a common message that can represent both Y and Z)
}

OR make large function into a method


#### Avoid Code Duplication
move common logic into a parent function and reuse it (module)

#### Comment the code & Common Naming Convention
eg in Java, class is first Letter uppercase Main.java
function is letter letter lowercase toLowerCase()
constant is all uppercase , final private int PIE = 3.14
