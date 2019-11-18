# Modify to different filenames within the same package dir path
MAIN_JAVA_FILENAME=Main

#Download from apache jena website:
# https://jena.apache.org/download/index.cgi
LIBS="./res/apache-jena-3.13.1/lib/*"
LOG4J="./res/apache-jena-3.13.1/"

# Java Stuff
TARGET=./target
MAIN_JAVA_PATH=./src/org/ontariotechu/
MAIN_EXEC=org.ontariotechu.

# Docker stuff
DOCKER_IMAGE=openjdk:8

all: docker-compile docker-run

#--------------------------LOCAL--------------------------
compile:
	javac -cp $(LIBS) -d $(TARGET) $(MAIN_JAVA_PATH)$(MAIN_JAVA_FILENAME).java;
run:
	java -cp $(TARGET):$(LIBS):$(LOG4J)  $(MAIN_EXEC)$(MAIN_JAVA_FILENAME);

#--------------------------DOCKER--------------------------
docker-compile:
	@echo "Compiling...";
	@docker run --rm -it -v $(PWD):/home/ -w /home/ $(DOCKER_IMAGE) javac -cp $(LIBS) -d $(TARGET) $(MAIN_JAVA_PATH)$(MAIN_JAVA_FILENAME).java;

docker-run:
	@echo "Running...";
	@docker run --rm -it -v $(PWD):/home/ -w /home/ $(DOCKER_IMAGE) java -cp $(TARGET):$(LIBS):$(LOG4J)  $(MAIN_EXEC)$(MAIN_JAVA_FILENAME);

test:
	docker run --rm -it -v $PWD:/home/ -w /home/ $(DOCKER_IMAGE) /bin/bash
