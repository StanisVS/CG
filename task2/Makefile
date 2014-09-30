
OUT= ./out/
JFLAGS = -g -d $(OUT)
JRFLAGS = -cp ./out/
JC = javac
JR = java

.SUFFIXES: .java .class
.java.class:
	 $(JC) $(JFLAGS) $*.java

CLASSES = \
	src/PointComparator.java \
	src/Point.java \
	src/Triangle.java \
	src/Main.java
	

default: outdir classes

outdir: 
	mkdir -p out

classes:
	$(JC) $(JFLAGS) src/*

run:
	$(JR) $(JRFLAGS) Main
	
clean:
	rm -rf $(OUT)*

