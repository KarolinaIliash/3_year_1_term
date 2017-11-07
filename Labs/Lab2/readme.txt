In this lab I created xml file and xsd scheme with following task:
Aircraft planes.
Description:
	•Model
	•Origin
	•Chars (more than one) –  type, seats amount(1 or 2), ammunition (rackets), radar
	•Parameters – length, height, wingspan(it's mine)
	•Price
Also I check whether this file is valid with scheme. If it's valid,
I parse it with one of three available parser(SAX, DOM, STAX) and
make list of planes. Sort this list and print it.
Also I made xslt file and make transform from my xml file to html file
newplanes.html