# Cryptography-Tools
This is a repository of all the cryptography tools I'll be making.

<h1>CryptographyProtocolTool</h1>
The first (and so far only project) added is the CryptographyProtocolTool.
The tool is a window that allows the user to play around with, test and build their own algorithms or the one's already included and use them in a GUI environment.
This is done by having a framework located in the package "core".

<h2>How to add a new Protocol</h2>
The framework is fully commented and located in the package "core". To add a new protocol, follow the steps
<ol>
<li>read over the Core package </li>
<li>Create a new package with the name of your protocol</li>
<li>Create a new class in the package that inherrits the Core.CryptoAlgo interface </li>
<li>Write the code for each method</li>
<li>pick or create a panel view for the Algorithm and inherrit that and fill in the information </li>
<li>option 1: write a main method that calls the showPopup(int) method of your window (for the protocol window to be its own app </li>
<li>option 2: add the view to where all the other views are intantiated and located (to include with others)</li>
</ol>

<h2>Why?</h2>
Having a tool like this allows ciphers to be cracked faster, algorithms to be tested/prototyped easier and allows for an easy way show off and use any algorithms you've created.
