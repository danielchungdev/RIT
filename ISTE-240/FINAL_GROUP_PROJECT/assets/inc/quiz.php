    <?php
    
        for ($i = 0; $i < 3; $i++) {
            
            if (($questions[$i][3]) != "") {
                unset($nums);
                $nums[0] = rand(0,3);
                for($x = 1; $x < 4; $x++) {
                    $n = rand(0,3);
                    while (in_array($n, $nums)) {
                        $n = rand(0,3);
                    }
                    
                    $nums[$x] = $n;
                }
        
                foreach ($nums as $n) {
                    if ($n == 3) {
                        $correct[] = "correct";
                    } else {
                        $correct[] = "incorrect";
                    }
                    $answers[] = $questions[$i][($n + 1)];
                }
                
                echo '<p class="question" id="q'.$i.'">'.$questions[$i][0].'</p>
                        <input class="radio" type="radio" name="a'.($i+1).'" value='.$correct[0].' id="A'.$i.'"><label class="radio_label" for="A'.$i.'">'.$answers[0].'</label><br/>
                        
                        <input class="radio" type="radio" name="a'.($i+1).'" value='.$correct[1].' id="B'.$i.'"><label class="radio_label" for="B'.$i.'">'.$answers[1].'</label><br/>
                        
                        <input class="radio" type="radio" name="a'.($i+1).'" value='.$correct[2].' id="C'.$i.'"><label class="radio_label" for="C'.$i.'">'.$answers[2].'</label><br/>
                        
                        <input class="radio" type="radio" name="a'.($i+1).'" value='.$correct[3].' id="D'.$i.'"><label class="radio_label" for="D'.$i.'">'.$answers[3].'</label><br/>';
            } else {
                
                if ($questions[$i][4] == "True") {
                    $tValue = "correct";
                    $fValue = "incorrect";
                } else {
                    $fValue = "correct";
                    $tValue = "incorrect";
                }
                
                echo '<p class="question" id="q'.$i.'">'.$questions[$i][0].'</p>
                        <input class="radio" type="radio" name="a'.($i+1).'" value='.$tValue.' id="T'.$i.'"><label class="radio_label" for="T'.$i.'">True</label><br/>
                        
                        <input class="radio" type="radio" name="a'.($i+1).'" value='.$fValue.' id="F'.$i.'"><label class="radio_label" for="F'.$i.'">False</label><br/>';
            }
            unset($correct);
            unset($answers);
        }    
                        
    ?>
    <input id="submit" class="button" type="submit">