# Demo File Storage in Android
A simple project to demonstrate how to work with Android File storage (internal and external)

##Internal Storage vs External Storage

All Android devices has 2 file storage areas:

*Internal Storage:* a built-in, non-violated memory for individual app

*External Storage:* a removable storage such as SD card. 

But nowadays, many devices divide its permanent memory into 2 part internal and external, in this case, external storage is not a **"removable storage"**.

*Comparison:*
| Internal Storage  | External Storage |

| Always available | Only available if the removable storage was mounted into the device  |

| By default, the internal storage is only accessible by its own application  | Word-readable  |

| If user uninstall the application, every data in internal storage will be deleted  | If user uninstall the application, all the data which was not stored in getExternalFileDir() folder will remain  |

