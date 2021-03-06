MAKEFLAGS += --warn-undefined-variables
SHELL = bash
.SHELLFLAGS = -eu -o pipefail -c
.DEFAULT_GOAL = help

## Usage
##    make <subcommand>
## Command: Description
## ~~~~~~~: ~~~~~~~~~~~



## build, b: compile all sources and package a jar with dependencies
b: build
.PHONY: build
build:
	@ mvn clean package

## run, r: run the program
r: run
.PHONY: run
run: build
	@ java -jar target/tic-tac-toe-*.jar

## test, t: run tests
t: test
.PHONY: test
test:
	@ mvn clean test

## compile: compile the project
.PHONY: compile
compile:
	@ mvn clean compile


.PHONY: help
all: help
help: Makefile
	@sed -n 's/^##//p' $< | column -t -s ':' |  sed -e 's/^/ /'
