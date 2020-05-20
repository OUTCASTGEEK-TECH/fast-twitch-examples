BASEDIR=$(PWD)
APPNAME=ogtech
PROJECTID=ogtech
REGION=us-east1

system-prep:
	curl -O https://download.clojure.org/install/linux-install-1.10.1.469.sh
	chmod +x linux-install-1.10.1.469.sh
	./linux-install-1.10.1.469.sh
	rm ./linux-install-1.10.1.469.sh
	curl -sL https://deb.nodesource.com/setup_13.x | bash -
	apt-get install build-essential nodejs -y

deps:
	rm -rf $(PWD)/node_modules; npm i

node-repl:
	npx shadow-cljs node-repl app

watch:
	npx shadow-cljs watch app web

compile:
	npx shadow-cljs compile app common web

release:
	npx shadow-cljs release app common web

deploy_ae:
	gcloud app deploy -v dev

container:
	gcloud builds submit --tag gcr.io/$(PROJECTID)/$(APPNAME)

deploy: container
	gcloud beta run deploy $(APPNAME) --image gcr.io/$(PROJECTID)/$(APPNAME) --platform managed --region $(REGION)
