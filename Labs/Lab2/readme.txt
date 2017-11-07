In this lab I created xml file and xsd scheme with following task:
¬оенные самолеты.
¬оенные самолеты можно описать по следующей схеме:
	ХModel Ц название модели.
	ХOrigin Ц страна производства.
	ХChars (должно быть несколько) Ц характеристики, могут быть следующими: тип (самолет поддержки, сопровождени€, истребитель, перехватчик, разведчик), кол-во мест (1 либо 2), боекомплект (есть либо нет [разведчик], если есть, то: ракеты [0 Ц 10]), наличие радара.
	ХParameters Ц длина (в метрах), ширина (в метрах), высота (в метрах).
	ХPrice Ц цена (в талерах).
Also I check whether this file is valid with scheme. If it's valid,
I parse it with one of three available parser(SAX, DOM, STAX) and
make list of planes. Sort this list and print it.
Also I made xslt file and make transform from my xml file to html file
newplanes.html