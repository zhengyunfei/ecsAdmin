function clearNoNum(obj){   
	obj.value = obj.value.replace(/[^\d.]/g,"");  

   obj.value = obj.value.replace(/^\./g,"");  

  obj.value = obj.value.replace(/\.{2,}/g,"."); 

  obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");

}