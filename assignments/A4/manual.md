# Manual

This is a manual that describes how to use the app SDPEncryptor.

This app has 3 inputs:
* Dispatch: message to be encoded
* Argument 0: first encryption parameter
* Argument 1: second encryption parameter

Dispatch should be a non-empty string and must contain at least one letter.

Argument 0 should be an integer coprime to 26 between 0 and 26.

Argument 1 should be an integer >= 1 and < 26.

There is 1 button:
* Encipher

If the inputs are valid, clicking the Encipher button encodes the dispatch and displays it under the label Encoded Dispatch.

If any input is invalid, clicking the Encipher button will put a message in the input field indicating as much.