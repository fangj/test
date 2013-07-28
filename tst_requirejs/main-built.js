
define('helper/a',[],function(){
console.log("a.js");
a=1;
this.b=3;
return 2;
});
require(["helper/a"],function(a){
console.log("main.js");
console.log("a="+a);
});

define("main", function(){});
