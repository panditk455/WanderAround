Personal Travel Journal

Why did we decide on this?

During our study abroad journey, we traveled extensively, and managing expenses, photos, and itineraries became quite challenging. Tracking expenses required maintaining a spreadsheet, which was cumbersome, while organizing photos and keeping track of what we did at specific places was equally overwhelming.   This experience inspired us to create a travel journal app that seamlessly integrates expense tracking, itinerary management, and multimedia storage, making it easier to relive and organize travel memories.  


Project Specification

Overview:  
WanderLog is a digital travel journal designed to let users document their adventures with photos, notes, locations, and expenses. It helps users relive their favorite trips, plan future ones, and share memories with friends. The app integrates maps, multimedia, and data visualization for a complete travel experience.  
WanderLog combines the personal, creative aspect of journaling with the utility of trip planning and expense tracking. Its unique blend of features offers a rich and memorable experience, appealing to travel enthusiasts who want to relive and organize their adventures digitally.

 Core Features

1. Trip Creation and Journaling:  
   - Create trips by entering details like destination, dates, and travel companions.  
   - Add daily journal entries with text, photos, and videos.  

2. Interactive Maps:  
   - Mark visited locations on an interactive map using Google Maps API.  
   - View all the locations from a trip as pins on the map.  

3. Photo Gallery:  
   - Display trip-specific photo albums using a LazyGrid layout.  
   - Allow users to add captions or short notes to each photo.  

4. Expense Tracker:  
   - Track travel expenses with categories (e.g., food, transport, accommodation).  
   - Visualize expenses using pie or bar charts (e.g., with the Vico library).  

5. Offline Mode:  
   - Allow journaling and viewing trips without internet access using Room for local data storage.  


 Advanced Features

1. Itinerary Planner:  
   - Create daily itineraries for trips, including activities, times, and locations.  
   - Show the itinerary as a timeline or in a calendar view.  

2. Travel Statistics:  
   - Summarize stats for each trip, such as total distance traveled, number of places visited, and money spent.  
   - Display statistics visually with animated graphs and charts.  

3. Collaborative Journals (Optional):  
   - Allow multiple users to contribute to the same journal (e.g., travel group members) using Firebase.  

4. Memory Highlights:  
   - Automatically create trip highlight reels with photos, captions, and stats, presented as a scrollable carousel or slideshow.  

 UI and Design Elements

- Navigation:  
   - Use `BottomNavigation` for main sections (Trips, Map, Photos, Expenses, Profile).  
   - Include a `FloatingActionButton` for quick journaling or adding photos.  

- Design Components:  
   - Use `Scaffold` and `TopAppBar` for clean navigation.  
   - Use `LazyColumn` and `LazyGrid` for displaying trip lists and photos.  
   - Incorporate animations for map interactions, chart displays, and trip transitions.  

 Technologies and Libraries

1. Compose UI Components:  
   - Scaffold, LazyColumn, LazyGrid, ConstraintLayout, FlowRow, and animations.  

2. APIs:  
   - Google Maps API for interactive maps and location tagging.  
   - Unsplash API for suggested destination photos or inspiration.  

3. Data Storage:  
   - Room for offline storage of journal entries, photos, and expenses.  
   - Firebase for collaborative journaling and backup.  

4. Visualization Tools:  
   - Vico or MPAndroidChart for expense and statistics charts.  

5. Image Loading and Capture:  
   - Coil for photo loading.  
   - Use the device’s camera to capture photos directly from the app.  

 Challenges and Complexity

- Integrating multimedia (photos, videos) with offline and online storage.  
- Designing an engaging interactive map for location tagging and trip visualization.  
- Implementing expense tracking and data visualization seamlessly.  


