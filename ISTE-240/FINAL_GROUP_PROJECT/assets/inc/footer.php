<div id="footer">
		<?php
			// retrieve and display the "last modified" date
			$filename = basename($_SERVER['PHP_SELF']);
			$file_last_modified = filemtime($filename);
			echo "Last modified: " . date("Y-m-d H:i", $file_last_modified)."\n";
		?>
</div>
