/// use -> it imports code from a library into your code
/// wasm-bindgen -> library (crate) used to communication between Rust and JavaScript
/// wasm_bindgen::prelude::* -> all scripts inside module prelude.
use wasm_bindgen::prelude::*;

/// Producing a rust code block that executes an external javascript function
/// This rust code block can be used inside a rust fn.
/// The bit inside the #[ ] is called an "attribute", and it modifies the next statement somehow.
/// The following statement is an extern, which tells Rust that we want to call some externally defined functions.
/// But the former attribute says "wasm-bindgen knows how to find these functions".
#[wasm_bindgen]
extern {
    /// A function signature, written in Rust. It says "the alert function takes one argument, a string named s."
    pub fn alert(s: &str);
}


/// Producing Rust functions that JavaScript can call
/// The bit inside the #[ ] is called an "attribute", and it modifies the next statement somehow.
/// The following statement is a fn, which tells Rust that we want to call some externally defined functions.
/// But the former attribute says "wasm-bindgen knows how to execute these functions, from a javascript call".
#[wasm_bindgen]
pub fn greet(name: &str) {
    /// This fn is named greet, and takes one argument, a string (written &str), name. (Rust Programming Language)
    /// This fn calls the alert function we asked for in the extern block above.
    /// It passes a call to the format! macro, which lets us concatenate strings. (this macro is from Rust Programming Language)
    /// The format! macro takes two arguments in this case, a format string, and a variable to put in it.
    /// This is passed to alert(), so when we call greet("NameOfSomebody") we will see an Js alert box with "Hello, NameOfSomebody!" in it.
    alert(&format!("Hello, {}!", name));
}
