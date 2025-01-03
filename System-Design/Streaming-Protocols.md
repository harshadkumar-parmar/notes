# Live streaming
### RTMP (Real-Time Messaging Protocol)
**Technical Details:**
- Developed by Adobe for streaming audio, video, and data over the internet.
- Operates over TCP, providing reliable delivery.
- Supports live video streaming and on-demand content.

**Advantages:**
- Low latency, making it suitable for live broadcasting.
- Widely supported by streaming software and platforms.

**Use Cases:**
- Live stream ingestion to streaming servers like YouTube Live, Facebook Live, and Twitch.
- Real-time interactive applications like live auctions and gaming.

### HLS (HTTP Live Streaming)
**Technical Details:**
- Developed by Apple, uses HTTP to deliver media content.
- Breaks down the video stream into small chunks (.ts files), which are indexed in a playlist (.m3u8 file).
- Supports adaptive bitrate streaming, adjusting quality based on network conditions.

**Advantages:**
- Highly compatible with web browsers and mobile devices.
- Scales easily using standard web infrastructure and CDNs.

**Use Cases:**
- Video on demand (VOD) services like Hulu, Disney+, and Netflix.
- Live streaming events like sports, concerts, and conferences.

### DASH (Dynamic Adaptive Streaming over HTTP)
**Technical Details:**
- An international standard for streaming media, also known as MPEG-DASH.
- Similar to HLS, it segments video into small chunks, indexed in a manifest file.
- Supports adaptive bitrate streaming for a smooth viewing experience.

**Advantages:**
- Codec-agnostic, supports various codecs like H.264, H.265, VP9, and AV1.
- Flexible and interoperable with different media players and devices.

**Use Cases:**
- Large-scale streaming services like YouTube and Vimeo.
- OTT (Over-the-Top) platforms providing live and on-demand content.

### WebRTC (Web Real-Time Communication)
**Technical Details:**
- Open-source project that enables real-time communication directly between browsers and devices.
- Uses RTP (Real-time Transport Protocol) for media delivery.
- Supports audio, video, and data channels.

**Advantages:**
- Very low latency, ideal for real-time applications.
- Peer-to-peer communication reduces the need for a central server.

**Use Cases:**
- Video conferencing platforms like Zoom, Google Meet, and Microsoft Teams.
- Interactive live streaming applications like online gaming and virtual classrooms.

### SRT (Secure Reliable Transport)
**Technical Details:**
- Developed by Haivision for secure and reliable transport of video data.
- Uses UDP for low-latency delivery and error correction to maintain video quality.
- Supports encryption for secure transmission.

**Advantages:**
- Low latency and high resilience to network issues.
- Secure transmission, protecting content from unauthorized access.

**Use Cases:**
- Professional live streaming for events, sports, and news broadcasting.
- Remote production and contribution workflows.

### RTP/RTSP (Real-Time Protocol / Real-Time Streaming Protocol)
**Technical Details:**
- RTP delivers audio and video over IP networks, while RTSP controls streaming media delivery.
- RTP uses UDP for real-time delivery, providing low latency.
- RTSP provides VCR-like control, allowing users to pause, rewind, and seek.

**Advantages:**
- Suitable for real-time control and interactive applications.
- Supports a wide range of media formats.

**Use Cases:**
- IP cameras and CCTV systems for real-time surveillance.
- Live streaming applications requiring precise control over playback.

### RTMFP (Real-Time Media Flow Protocol)
**Technical Details:**
- Developed by Adobe for peer-to-peer communication.
- Uses UDP for efficient bandwidth usage and low latency.
- Supports secure communication with end-to-end encryption.

**Advantages:**
- Efficient bandwidth usage, reducing server load.
- Low latency, suitable for real-time applications.

**Use Cases:**
- Peer-to-peer live streaming applications.
- Real-time communication in multiplayer online games.

### HDS (HTTP Dynamic Streaming)
**Technical Details:**
- Developed by Adobe for streaming media over HTTP.
- Similar to HLS and DASH, it segments video into small chunks for delivery.
- Uses HTTP servers and standard web infrastructure.

**Advantages:**
- Compatible with Adobe Flash Player and other media players.
- Supports adaptive bitrate streaming.

**Use Cases:**
- Video on demand (VOD) services.
- Live streaming events using Adobe's media ecosystem.
