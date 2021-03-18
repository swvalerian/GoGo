package main.repository;

import main.model.Skill;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SkillRepository {
    Integer id;
    String name;
    private Skill skill;
    private List<Skill> skillList;

    File file = new File("src/main/resources/files/skills.txt");

    public SkillRepository() {
        fileCreate(); // файл создается, если его нету... я потом удалю это
        getAll(); // реализовал ужасно криво!
        // есть идеи сделать через BufferedReader reader = new BufferedReader( new InputStreamReader(file)));
        getById(4); // метод работает
    }

    // файл создается, если его нету.
    private void fileCreate(){
        if (!file.exists()) {
            try {
                System.out.println(file.createNewFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Skill> getAll() {
        skillList = new ArrayList<>();

        try (FileReader fr = new FileReader(file)) {
            int c;
            String str = "";
            name = "";
            // прочитать

            while ((c = fr.read()) != -1) {
                str = str + (char) c;

                while ((c = fr.read()) != (int) ',' ) {
                    str = str + (char) c;
                }

                id = Integer.decode(str); // из строки в число, для дальнейшей записи в список по id

                while (((c = fr.read()) != '\n') && (c != -1)) {
                    name += (char) c;
                }

                skillList.add(new Skill(id, name)); // добавляю новый элемент в список

                str = "";
                name = "";

            }
        } catch (IOException ex) {
            System.out.println("понял да, отловил исключуху");
        }

//        for (Skill in : skillList) {
//            System.out.println(in.getId() + " : " + in.getName());
//        }
        return skillList;
    }

    public Skill getById(Integer id) {
//        System.out.println(skillList.get(id));
        return skillList.get(id);
    }
}
