# object_manager_framework
Simple framework that helps u to write clean code (aka Spring)
factory package consists of all backend logic of the framework
test package consists of test classes aka simple service that demonstrates how to work with the framework
Annotations that are supported for now:
 - @InjectProperty - set field from the application.properties file by name of the filed or annotation param.
   ![image](https://github.com/Zaby2/object_manager_framework/assets/109461366/d8931faa-9203-4ec3-8668-50dc1aedf348)

   
- @Singleton
  
  ![image](https://github.com/Zaby2/object_manager_framework/assets/109461366/c88c46d6-63cf-490f-a390-d27807bf5293)

  
- InjectByType - my own Autowired realisation

  
  ![image](https://github.com/Zaby2/object_manager_framework/assets/109461366/c7314209-2ee3-4c92-b752-efb7b08a688b)


TODO:
- Ioc need to be added ✓
- Singleton cache need to be added ✓
- Dynamic proxy need to be added
- need to create special context to avoid using getInstance() ✓
- Lazy singletones stuff
