package gui;

import java.io.File;

/**
 * <h1>This class simply stores file paths</h1>
 *
 * @author Tanner Leonard
 * @version 1.0
 * @since 2018-09-29
 */
public class FilePaths {
    // a string to hold the file paths
    public String[] paths;
    // get an example file path
    private String filePath = new File("").getAbsolutePath();

    public FilePaths() {
        // the length of the example file path
        int filePathLen = filePath.length();
        // controls whether the file path needs to include "src/"
        boolean includeSrc = true;

        // checks to see if the the example file path ends in "src/"
        if (filePath.charAt(filePathLen - 1) == 'c' && filePath.charAt(filePathLen - 2) == 'r' &&
                filePath.charAt(filePathLen - 3) == 's') {
            // if so, we don't need to include "src/" in the path
            includeSrc = false;
        }

        if (includeSrc) {
            // simply set all of the path names

            paths = new String[13];
            paths[0] = "src/gui/gui_elements/Assets/hover.mp3";
            paths[1] = "src/gui/gui_elements/Assets/click.mp3";

            paths[2] = "src/gui/gui_elements/Assets/rcp_title.png";

            paths[3] = "src/gui/gui_elements/Assets/question_mark.png";

            paths[4] = "src/gui/gui_elements/Assets/cartoon_rock.png";
            paths[5] = "src/gui/gui_elements/Assets/cartoon_paper_final.png";
            paths[6] = "src/gui/gui_elements/Assets/cartoon_scissors_final.png";

            paths[7] = "src/gui/gui_elements/Assets/green_square.png";
            paths[8] = "src/gui/gui_elements/Assets/red_circle.png";
            paths[9] = "src/gui/gui_elements/Assets/blue_triangle.png";
            paths[10] = "src/gui/gui_elements/Assets/user_image.png";

            paths[11] = "src/gui/gui_elements/Assets/losing_sound.mp3";
            paths[12] = "src/gui/gui_elements/Assets/winning_sound.mp3";
        } else {
            // simply set all of the path names

            paths = new String[13];
            paths[0] = "gui/gui_elements/Assets/hover.mp3";
            paths[1] = "gui/gui_elements/Assets/click.mp3";

            paths[2] = "gui/gui_elements/Assets/rcp_title.png";

            paths[3] = "gui/gui_elements/Assets/question_mark.png";

            paths[4] = "gui/gui_elements/Assets/cartoon_rock.png";
            paths[5] = "gui/gui_elements/Assets/cartoon_paper_final.png";
            paths[6] = "gui/gui_elements/Assets/cartoon_scissors_final.png";

            paths[7] = "gui/gui_elements/Assets/green_square.png";
            paths[8] = "gui/gui_elements/Assets/red_circle.png";
            paths[9] = "gui/gui_elements/Assets/blue_triangle.png";
            paths[10] = "gui/gui_elements/Assets/user_image.png";

            paths[11] = "gui/gui_elements/Assets/losing_sound.mp3";
            paths[12] = "gui/gui_elements/Assets/winning_sound.mp3";
        }
    }
}
