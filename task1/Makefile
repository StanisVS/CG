
OUT= ./out/
JFLAGS = -g -d $(OUT)
JRFLAGS = -cp ./out/
JC = javac
JR = java
PACKAGE = ru/spbau/voronchikhin/task1/


.SUFFIXES: .java .class
.java.class:
	 $(JC) $(JFLAGS) $*.java

CLASSES =\
	src/$(PACKAGE)Main.java

default: outdir classes

outdir: 
	mkdir -p out

classes: $(CLASSES:.java=.class)

run:
	$(JR) $(JRFLAGS) $(PACKAGE)Main
	
clean:
	rm -rf $(OUT)*

