package com.peakycoders.filmy.ui.utils

class Utils(){
     companion object{
          fun genURL_img(resource : String?) : String{
               if(resource.isNullOrBlank()){
                    return "https://www.haedosrl.com.ar/images/frontend/notfound.png"
               }
               return "https://image.tmdb.org/t/p/original/$resource"
          }
     }
}