interface NameGenerator{
    abstract  fun getLayoutName(name:String):String;
    abstract fun getControllerName(name:String):String;
    abstract fun getModelName(name:String):String;
    fun getCorrectNameCreation(name:String):String{
        var newName:String = name;
        // removing special Characters and numbers
        val re = Regex("[^A-Za-z]");
        newName = re.replace(name,"");
        return newName;
    }
}

class Android : NameGenerator{
    override fun getLayoutName(name:String):String {
        var layoutName = name.lowercase();
        layoutName = layoutName.replace(" ","_");
        return "activity_$layoutName";
    }
    override fun getControllerName(name:String):String {
        var controllerName = name.replace(" ","");
        return "${controllerName}Activity";
    }

    override fun getModelName(name:String):String {
        var modelName = name.replace(" ","");
        return "${modelName}Model";
    }
}

class Flutter: NameGenerator{
    override fun getLayoutName(name:String):String {
        var layoutName = name.lowercase();
        layoutName = layoutName.replace(" ","_");
        return "$layoutName";
    }
    override fun getControllerName(name:String):String {
        var controllerName = name.split(" ");
        return "${controllerName[0].lowercase()}_controller";
    }

    override fun getModelName(name:String):String {
        var modelName = name.split(" ");
        return "${modelName[0].lowercase()}_model";
    }
}

class iOS: NameGenerator{
    override fun getLayoutName(name:String):String {
        var viewName = name.replace("","");
        return "${viewName}View";
    }
    override fun getControllerName(name:String):String {
        return "----No Controller Needed----";
    }

    override fun getModelName(name:String):String {
        var viewModelName = name.replace("","");
        return "${viewModelName}ViewModel";
    }
}

fun main(args:Array<String>) {
    val technology = args[0];
    var name = args[1];
    val tech = when(technology.lowercase()){
        "android" -> Android();
        "flutter" -> Flutter();
        "ios" -> iOS();
        else ->  Android();
    }
    name = tech.getCorrectNameCreation(name);
    println("LayoutName: for $technology is ${tech.getLayoutName(name)}");
    println("ControllerName: for $technology is ${tech.getControllerName(name)}");
    println("ModelName: for $technology is ${tech.getModelName(name)}");
    return;
}