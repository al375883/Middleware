
JFLAGS = -cp .
JC = javac
JVM= java 
FILE=

.SUFFIXES: .java .class


.java.class:
	$(JC) $(JFLAGS) ./midd/rmi/*.java

MAIN_S = Servidor
MAIN_C = ClienteApp

default: .java.class

run_s: ./midd/rmi/$(MAIN_S).class
	$(JVM) $(JFLAGS) midd.rmi.$(MAIN_S)

run_c: ./midd/rmi/$(MAIN_C).class
	$(JVM) $(JFLAGS) midd.rmi.$(MAIN_C)

clean:
	$(RM) ./midd/rmi/*.class
	$(RM) ./midd/rmi/*~
