In this lab I created xml file and xsd scheme with following task:
������� ��������.
������� �������� ����� ������� �� ��������� �����:
	�Model � �������� ������.
	�Origin � ������ ������������.
	�Chars (������ ���� ���������) � ��������������, ����� ���� ����������: ��� (������� ���������, �������������, �����������, �����������, ���������), ���-�� ���� (1 ���� 2), ����������� (���� ���� ��� [���������], ���� ����, ��: ������ [0 � 10]), ������� ������.
	�Parameters � ����� (� ������), ������ (� ������), ������ (� ������).
	�Price � ���� (� �������).
Also I check whether this file is valid with scheme. If it's valid,
I parse it with one of three available parser(SAX, DOM, STAX) and
make list of planes. Sort this list and print it.
Also I made xslt file and make transform from my xml file to html file
newplanes.html