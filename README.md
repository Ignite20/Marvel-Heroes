# Marvel-Heroes  
This is a repository for the marvel heroes

It hosts the Android code for an app that consumes the Marvel API. It's and example of the implementation for ViewModel arquitecture among other features.

Some of the test examples:

**Simple**
	

    @Test  
    	fun t1_when_request_heroes_list_from_repository() {  
    	   val heroesResponse = SingleUseCaseImplementation<List<Hero>>()  
    	   repository.getHeroes(heroesResponse, 0)  
    	 
    	   assertNotNull(heroesResponse.data)  
    	}
    @Test  
        fun t2_when_creating_md5_receive_result() {  
            assertNotNull(calculateMD5("23", "test", "string"))  
        }

**Complex**

        @Test  
    fun t1_when_requesting_heroes_list_then_recover() {  
        interactor.getHeroes(object : MarvelCallback {  
            override fun onHeroesListOK(list: MutableList<Hero>) {  
                assertNotNull(list)  
                assertNotEquals(0, list.size)  
            }  
      
            override fun onHeroesListKO(message: ErrorMessage?) {  
      
            }  
      
            override fun onHeroOK(hero: Hero) {  
      
            }  
      
            override fun onHeroKO(message: ErrorMessage?) {  
      
            }  
        })  
    }
    
