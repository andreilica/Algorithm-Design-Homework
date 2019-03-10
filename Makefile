
build: Frati Numaratoare 

Frati:
	javac src/Frati.java -d .

Numaratoare: 
	javac src/Numaratoare.java -d .

run-p1:
	java Frati

run-p4: 
	java Numaratoare

clean:
	rm -rf Frati Numaratoare *.class
