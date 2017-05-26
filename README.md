# rule-engine

### 1. Clone and build EasyRules
```
$ git clone https://github.com/j-easy/easy-rules.git
$ cd easy-rules
$ mvn install
```

### 2. Clone, build and run this application
```
$ git clone https://github.com/acntech/rule-engine.git
$ cd rule-engine
$ mvn install spring-boot:run
```

### 3. Access the compositerule through its REST-interface
Sample 1: http://localhost:8080/legalRegistree/1980-05-25/male   **(Legal - Male and old enough)**

Sample 2: http://localhost:8080/legalRegistree/1980-05-25/female   **(Illegal - Female and old enough)**

Sample 3: http://localhost:8080/legalRegistree/2007-05-25/male   **(Illegal - Male and underage)**

Sample 4: http://localhost:8080/legalRegistree/2007-05-25/female   **(Illegal - Female and underage)**

### 4. Access the agerule through its REST-interface
Sample 1: http://localhost:8080/oldEnough/1980-05-25   **(Legal - Old enough)**

Sample 2: http://localhost:8080/oldEnough/2007-05-25   **(Illegal - Female and old enough)**

### 5. Access the genderrule through its REST-interface
Sample 1: http://localhost:8080/legalGender/male   **(Legal - Male)**

Sample 2: http://localhost:8080/legalGender/female   **(Illegal - Female)**
