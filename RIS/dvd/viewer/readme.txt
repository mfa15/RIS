This autorun package is intended for distribution as a patient CD/DVD viewer on CD/DVD media with a DICOM study.

The contents of the zipped archive after unpacking should be placed in the root folder of a CD/DVD media (without this readme.txt file).
You need your own hardware and software to transfer the autorun package contents to the media (e.g. a disc publisher).

The package contains 32-bit (RA32 folder) and 64-bit (RA64 folder) versions of RadiAnt DICOM Viewer CD/DVD.
There is also the "COMMON" folder, which contains the following subfolders and files:

"LANG" - the language XML files with RadiAnt DICOM Viewer CD/DVD translations may be placed in this folder.
"LICENSE" - a folder with the license agreements for RadiAnt DICOM Viewer CD/DVD and used open source libraries.
"logo.jpg" - this file may be replaced with your custom logo image (JPEG, 650x300px).
"options.xml" - this file may be edited to change the default settings of the RadiAnt DICOM Viewer CD/DVD.
"overlay.xml" - this file may be edited to change the default annotations overlayed over the images.

After the purchase you will receive a license key (one key per one recording device).
The license key file ("license.radiantlic", which name may be changed to "license.rlc" for the sake of compatibility with the ISO 9660 Level 1 file system) must be copied to the "COMMON" folder.

After inserting media, the "autorun.inf" file automatically opens RadiAnt DICOM Viewer CD/DVD which performs a search for DICOM files placed on the media.
This search can be narrowed down to the specific folders defined as command-line parameters (-d) in the "autorun.inf" file:

[AutoRun.Amd64]
LABEL=RadiAnt DICOM Viewer CD/DVD 64-bit
ICON=RA64\viewer64.exe,0
OPEN=RA64\viewer64.exe -d IMAGES

[AutoRun]
LABEL=RadiAnt DICOM Viewer CD/DVD 32-bit
ICON=RA32\viewer32.exe,0
OPEN=RA32\viewer32.exe -d IMAGES

The viewer may be also started manually with the "run.bat" file. It opens the "RA32/launcher.exe" tool which, depending on user's operating system versions, runs either 32-bit or 64-bit version of RadiAnt DICOM Viewer CD/DVD.
