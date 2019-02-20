/**
 * Copyright (C) 2012-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Copyright (C) 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.inject.Singleton;
import model.qa;
import ninja.Result;
import ninja.Results;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


@Singleton
public class Practice {

//    mvn clean install ninja:run -Dninja.port=9999

    public Result index() {
        String question = "";
        String[] answers = null;
        String solution = "";
try {

    Random rnd = new Random();
    String fname = "q"+(rnd.nextInt(7)+1)+"_parsed.json";

    System.out.println("http://localhost:9999/assets/json/"+fname);

    String out = new Scanner(new URL("http://localhost:9999/assets/json/"+fname).openStream(), "UTF-8").useDelimiter("\\A").next();
    Gson gson = new Gson();
    ArrayList<qa> arr = gson.fromJson(out, new TypeToken<List<qa>>(){}.getType());

    qa rqa = arr.get(rnd.nextInt(arr.size()));

    question = rqa.getQuestion();
    solution = rqa.getCorrectAnswers();
    answers = rqa.getAnswers().split("\n");

    System.out.println(gson.toJson(rqa));
}catch (Exception e){e.printStackTrace();

   return Results.redirect("/practice");
}


if(question==null || solution==null || answers==null ||answers.length<=0){
    return Results.redirect("/practice");
}

if(question.length()<=0 || solution.length()<=0){
    return Results.redirect("/practice");
}

        return Results.html().render("question",question)
                .render("solution",solution)
                .render("answers",answers)
                ;

    }
    


}
